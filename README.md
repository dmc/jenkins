# heroku de Jenkins

This is Template FilesSet of Jenkins to deploy Heroku

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

# Running Locally

Make sure you have Java and [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/dmc/jenkins.git
$ cd jenkins
$ heroku local web
```

Jenkins should now be running on [localhost:8080](http://localhost:8080/).

- login user : admin
- password : admin

# Restore plugins

execute [deployPlugins.groovy](./deployPlugins.groovy) from script console

# Backup plugins

execute [getPluginList.groovy](./getPluginList.groovy) from script console. 

- copy result as [deployPlugins.groovy](./deployPlugins.groovy)

# Restore cloud config

execute [configureCloud.groovy](./configureCloud.groovy) from script console

- Required Credencials on Jenkins before execute script 
  - AWS Credencials
  - EC2 Private Key(SSH Private Key)
