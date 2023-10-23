echo "get diff"
git pull


echo "set java version to 17"
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

source ~/.bashrc


echo "Build..."
./build.sh

echo "Start API..."
./start.sh
