import com.amazonaws.services.ec2.model.InstanceType
import com.cloudbees.plugins.credentials.domains.Domain
import hudson.model.*
import hudson.plugins.ec2.AmazonEC2Cloud
import hudson.plugins.ec2.AMITypeData
import hudson.plugins.ec2.ConnectionStrategy
import hudson.plugins.ec2.EC2Tag
import hudson.plugins.ec2.HostKeyVerificationStrategyEnum
import hudson.plugins.ec2.SlaveTemplate
import jenkins.model.Jenkins

awsCredentialsId = "aws"
region = "ap-northeast-1"
ec2CloudName = "aws"
ec2PrivateKeyName = "ec2-private-key"

amazonLinux = new SlaveTemplate(
    "ami-0ecb2a61303230c9d",    //String ami,
    "",                         //String zone,
    null,                       //SpotConfiguration spotConfig,
    "jenkins-security-group",   //String securityGroups,
    "/home/ec2-user",           //String remoteFS,
    InstanceType.T2Micro,       //com.amazonaws.services.ec2.model.InstanceType type,
    false,                      // boolean ebsOptimized,
    "amazon-linux",             //String labelString,
    Node.Mode.EXCLUSIVE,        //Node.Mode mode,
    "amazon-linux",             // description,
    "sudo yum install -y java-11-amazon-corretto-headless git", //String initScript,
    "",                         //String tmpDir,
    "",                         //String userData,
    "1",                        //String numExecutors,
    "ec2-user",                 //String remoteAdmin,
    null,                       //AMITypeData amiType,
    "",                         //String jvmopts,
    false,                      //boolean stopOnTerminate,
    "",                         //String subnetId,
    [new EC2Tag('Name', 'jenkins.slave.jdk11')], //List<EC2Tag> tags,
    "15",                       //String idleTerminationMinutes,
    false,                      //boolean usePrivateDnsName,
    "",                         //String instanceCapStr,
    "",                         //String iamInstanceProfile,
    false,                      //boolean deleteRootOnTermination,
    false,                      //boolean useDedicatedTenancy,
    false,                      //boolean useEphemeralDevices,
    "",                         //String launchTimeoutStr,
    false,                      //boolean associatePublicIp,
    "",                         //String customDeviceMapping,
    false,                      //boolean connectBySSHProcess,
    false)                      //boolean connectUsingPublicIp

amazonLinux.connectionStrategy = ConnectionStrategy.PUBLIC_DNS
amazonLinux.hostKeyVerificationStrategy = HostKeyVerificationStrategyEnum.OFF

jenkins = Jenkins.getInstance()
store = jenkins.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].store
ec2PrivateKey = store.getCredentials(Domain.global()).find {
    it.getId() == ec2PrivateKeyName
}


cloud = new AmazonEC2Cloud(
    ec2CloudName,               //String cloudName,
    false,                      //boolean useInstanceProfileForCredentials,
    awsCredentialsId,           //String credentialsId,
    region,                     //String region,
    ec2PrivateKey.privateKey,   //String privateKey,
    ec2PrivateKeyName,          //String sshKeysCredentialsId,
    "2147483647",               //String instanceCapStr,
    [amazonLinux],              //List < ? extends SlaveTemplate > templates,
    "",                         //String roleArn,
    "")                         //String roleSessionName

jenkins.clouds.add(cloud)
jenkins.save()
