SUMMARY = "Image for the collaborative Pasta Demo with AWS and NXP"
DESCRIPTION = "Collaborative demo with Amazon and NXP to plug the pasta demo \
to AWS services such as AWS IoT Greengrass and AWS SageMaker Neo. Built on top \
of TorizonCore with Docker runtime."

require recipes-images/images/torizon-core-container.inc

# Ideally we remove those when the project is finished
CORE_IMAGE_DEV_PKGS = " \
    packagegroup-core-buildessential \
    packagegroup-core-sdk \
    git \
    cmake \
"

# AWS Greengrass packages
CORE_IMAGE_AWS_GREENGRASS= " \
    aws-iot-greengrass-core-software \
    aws-iot-greengrass-core-software-init \
    aws-iot-greengrass-core-software-certificate \
    greengrass-dependency-checker \
"

# We need those for running AWS Greengrass SDK on the host
CORE_IMAGE_PYTHON_PKGS = " \
    python-modules python3-modules \
    python-pip python3-pip \
    python-psutil python3-psutil \
    python-flask python3-flask \
    python-flask-restful python3-flask-restful \
    python-flask-jsonpify python3-flask-jsonpify \
    python-greengrasssdk python3-greengrasssdk \
"

# We need those for running AWS Greengrass SDK on the host
CORE_IMAGE_NODEJS_PKGS = " \
    nodejs nodejs-npm \
"

# We need those for running AWS Greengrass SDK on the host
# CORE_IMAGE_JAVA_PKGS = " \
#     openjre-8
# "

CORE_IMAGE_BASE_INSTALL += " \
    docker \
    python3-docker-compose \
    ${CORE_IMAGE_DEV_PKGS} \
    ${CORE_IMAGE_PYTHON_PKGS} \
    ${CORE_IMAGE_NODEJS_PKGS} \
    ${CORE_IMAGE_AWS_GREENGRASS} \
"

EXTRA_USERS_PARAMS += "\
usermod -a -G docker torizon; \
groupadd -r ggc_group; \
useradd -r -g ggc_group -p '' ggc_user; \
"