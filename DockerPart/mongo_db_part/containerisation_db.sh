docker build -t mongodb_codephenomremastered .
docker run -d -it --name mongodb -p 27017:27017 -v /var/lib/mongodb:/data/db mongodb_codephenomremastered
docker  exec -it  mongodb bash


