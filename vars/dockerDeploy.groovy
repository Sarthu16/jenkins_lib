def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Missing 'imageName'")
    def tag = config.tag ?: "latest"
    def containerName = config.containerName ?: "flask-container"
    def port = config.port ?: "5000"

    sh """
        docker rm -f ${containerName} || true
        docker pull ${imageName}:${tag}
        docker run -d --name ${containerName} -p ${port}:${port} ${imageName}:${tag}
    """
}
