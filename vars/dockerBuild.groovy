def call(Map config = [:]) {
    def imageName = config.imageName ?: error("imageName is required")
    def tag = config.tag ?: 'latest'

    sh "docker build -t ${imageName}:${tag} ."
}
