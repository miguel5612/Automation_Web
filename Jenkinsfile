#!groovy

import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()

pipeline {
    environment {
        team = "QA"
        productName = "Prueba"
        branchName = "master"
        jiraProjectVersion = "1.0"
        jiraServerAddress = "https://atlassian.net"
        sourceCodeURL = "https://github.com/miguel5612/Automation_Web"
    }
    tools {
        gradle "gradle"
    }
    agent { node { label 'Windows-builder' } }
    options { buildDiscarder logRotator( daysToKeepStr: '16',numToKeepStr: '10') }
    stages {
        stage('Setup parameters') {
            steps {
                script {
                    properties([
                        parameters([
                            text('name'), 
                            text('description'),
                            choice(
                                choices: ['Runner', 'RunnerPersonalizado'], 
                                name: 'runner'
                            ),
                            choice(
                                choices: ['smokeTest', 'iniciarSesion', 'transferencias'], 
                                name: 'tagName'
                            ),
                            choice(
                                choices: ['TEST', 'AUTO'], 
                                name: 'projectKey'
                            )
                        ])
                    ])
                }
            }
        }
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Code Checkout') {
            steps {
                script {
                    checkout([
                        $class: "GitSCM", 
                        branches: [[ name: "${branchName}" ]],
                        userRemoteConfigs: [[ url: "${sourceCodeURL}" ]]
                    ])
                }
            }
        }
        stage('Execute tests') {
            steps {
                script {
                    try {
                        //bat("gradle clean test -Dcucumber.options=\"--tags ${params.tagName}\" aggregate")
                        //bat("gradle clean test -PcucumberArgs=\"--tags ${params.tagName}\" aggregate")
                        //bat("gradle clean test aggregate --info")
                        bat("gradle clean test --tests *${params.runner}* -Dtags=\"@${params.tagName}\" aggregate")
                        echo 'Test Ejecutados sin Fallo'
                        currentBuild.result = 'SUCCESS'
                    } catch (ex) {
                        echo 'Test Ejecutados con Fallo'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
       
        stage('Generate evidence') {
            steps {
                script {
                    try {
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: "${WORKSPACE}//target//site//serenity",
                            reportFiles: 'index.html',
                            reportName: 'Evidencias Automatizacion',
                            reportTitles: 'WEB'
                        ])
                        echo 'Reporte Html realizado con exito'
                    } catch (e) {
                        echo 'Generacion fallida'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }
}