import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.plugin.CpsJobDslScriptLoader;
import javaposse.jobdsl.plugin.ExecuteDslScripts;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.jenkinsci.plugins.workflow.job.WorkflowJobProperty;
import hudson.model.FreeStyleProject;
import hudson.util.RunList;
import jenkins.model.Jenkins;

public class JenkinsPipelineGenerator {

    public static void main(String[] args) throws Exception {
        Jenkins jenkins = Jenkins.get();

        // Define Jenkins DSL script
        String dslScript = generateJenkinsDslScript();
        
        // Execute the DSL script to create the job
        DslScriptLoader scriptLoader = new CpsJobDslScriptLoader();
        ExecuteDslScripts executeDslScripts = new ExecuteDslScripts();
        executeDslScripts.setTargets(dslScript);
        executeDslScripts.setIgnoreExisting(false);
        executeDslScripts.setAdditionalClasspath("");
        scriptLoader.runScript(executeDslScripts);

        // Optionally, trigger a build
        WorkflowJob job = jenkins.getItemByFullName("MyPipelineJob", WorkflowJob.class);
        job.scheduleBuild2(0);
    }

    public static String generateJenkinsDslScript() {
        return """
            pipelineJob('MyPipelineJob') {
                definition {
                    cps {
                        script {
                            agent any
                            stages {
                                stage('Build') {
                                    steps {
                                        sh './gradlew assemble'
                                    }
                                }
                                stage('Test') {
                                    steps {
                                        sh './gradlew test'
                                    }
                                }
                                stage('Deploy') {
                                    steps {
                                        echo 'Deploying...'
                                    }
                                }
                                stage('Deploy to Production') {
                                    when {
                                        branch 'master'
                                    }
                                    steps {
                                        echo 'Deploying to production, Ernest...'
                                    }
                                }
                            }
                            post {
                                success {
                                    echo 'Build succeeded, Ernest!'
                                    emailext (
                                        to: 'devops@example.com',
                                        subject: "Build Successful: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                                        body: "Build successful, Ernest! Check console output at ${env.BUILD_URL}"
                                    )
                                }
                                failure {
                                    echo 'Build failed, Ernest!'
                                    emailext (
                                        to: 'devops@example.com',
                                        subject: "Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                                        body: "Build failed, Ernest! Check console output at ${env.BUILD_URL}"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        """;
    }
}
