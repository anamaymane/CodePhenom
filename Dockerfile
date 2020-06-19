FROM debian:buster
RUN apt update && apt install -y \
	python3 \
	vim \
	gcc \
	time \
	g++
RUN apt-get install -y valgrind
COPY compile.sh /compile.sh
#RUN /compile.sh $PROBLEM_ID $SUBMISSION_ID $LANG $TIME_LIMIT $MEMORY_LIMIT









#RUN export http_proxy='http://10.23.201.11:3128' && export https_proxy='http://10.23.201.11:3128'
###RUN touch /etc/profile.d/proxy.sh && printf "export http_proxy='http://10.23.201.11:3128'\nexport https_proxy='http://10.23.201.11:3128'\n" > /etc/profile.d/proxy.sh
###RUN touch /etc/apt/apt.conf.d/99HttpProxy && printf "Acquire::http::Proxy \"http://10.23.201.11:3128\";\n" > /etc/apt/apt.conf.d/99HttpProxy
#RUN printf "http_proxy='http://10.23.201.11:3128'\nhttps_proxy='http://10.23.201.11:3128'" > /etc/environment
