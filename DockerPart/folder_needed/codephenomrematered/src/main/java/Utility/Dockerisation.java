package Utility;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import java.util.ArrayList;
import java.util.List;

public class Dockerisation {
    String scriptPath = "/problems/compile.sh";
    private String languageName;
    private String submissionId;
    private String problemId;
    private String timeLimit;
    private String memoryLimit;

    public Dockerisation(String language,String sub,String prob, String timeLimit2, String memLimit) {
        languageName = language;
        submissionId = sub;
        problemId = prob;
        timeLimit = timeLimit2;
        memoryLimit = memLimit;
        //


        // Docker client config
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        // Docker client init
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://172.17.0.1:2375").build();
        // Volumes settings
        String submissionsVolumeHost = "/submissions";
        String submissionsVolumeContainer = "/submissions";
        String problemsVolumeHost = "/problems";
        String problemsVolumeContainer = "/problems";

        // Container run cmds list
        List<String> cmdList = new ArrayList<>();
        cmdList.add(scriptPath);
        cmdList.add(problemId);
        cmdList.add(submissionId);
        cmdList.add(languageName);
        cmdList.add(timeLimit);
        cmdList.add(memoryLimit);

        // Container create
        CreateContainerResponse container4
                = dockerClient.createContainerCmd("compilation_image")
                .withCmd(cmdList)
                .withBinds(
                        Bind.parse(submissionsVolumeHost + ":" + submissionsVolumeContainer),
                        Bind.parse(problemsVolumeHost + ":" + problemsVolumeContainer)
                ).exec();
        // Container run
        dockerClient.startContainerCmd(container4.getId()).exec();

        // Wait for container to finish execution
        dockerClient.waitContainerCmd(container4.getId());
    }
}
