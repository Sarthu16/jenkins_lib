def call(Map config = [:]) {
    def imageName = config.imageName ?: 'my-image'
    def tag       = config.tag ?: 'latest'
    def dockerfile = config.dockerfile ?: 'Dockerfile'
    def buildContext = config.buildContext ?: '.'

    echo "🔧 Building Docker image: ${imageName}:${tag}"
    echo "📄 Using Dockerfile: ${dockerfile}"
    echo "📦 Build context: ${buildContext}"

    sh """
        docker build -t ${imageName}:${tag} -f ${dockerfile} ${buildContext}
    """

    echo "✅ Docker image ${imageName}:${tag} built successfully!"
}
