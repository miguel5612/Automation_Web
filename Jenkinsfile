pipeline {
    agent any  // Utiliza cualquier agente disponible en Jenkins

    environment {
        team = "QA"
        productName = "Prueba"
        branchName = "master"
        sourceCodeURL = "https://github.com/miguel5612/Automation_Web"
    }

    options {
        buildDiscarder logRotator(daysToKeepStr: '16', numToKeepStr: '10')
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()  // Limpia el espacio de trabajo
            }
        }

        stage('Code Checkout') {
            steps {
                checkout scm  // Realiza la descarga del código desde el repositorio configurado
            }
        }

        stage('Execute Tests') {
            steps {
                echo "Ejecutando tests simples"
                // Simula la ejecución de tests
                sh "echo 'Test results: SUCCESS'"
            }
        }

        stage('Generate Report') {
            steps {
                echo "Generando reporte simple"
                // Simula la generación de un reporte
                sh "echo 'Reporte generado'"
            }
        }
    }

    post {
        always {
            echo "Proceso completado"
        }
    }
}
