def call(Map config = [:]) {
    def imageName = config.imageName ?: error("imageName is required")
    def tag       = config.tag ?: 'latest'
    def registry  = config.registry ?: 'docker.io'
    def credentialsId = config.credentialsId ?: error("credentialsId is required")

    def fullImageName = "${registry}/${imageName}:${tag}"

    echo "🔐 Logging into Docker Hub and pushing image: ${fullImageName}"

    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh """
            echo "${DOCKER_PASS}" | docker login -u "${DOCKER_USER}" --password-stdin ${registry}
            docker tag ${imageName}:${tag} ${fullImageName}
            docker push ${fullImageName}
            docker logout ${registry}
        """
    }

    echo "✅ Image pushed successfully to: ${fullImageName}"
}
