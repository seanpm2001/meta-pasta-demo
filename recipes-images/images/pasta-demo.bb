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
    weston-examples \
    gtk+3-demo \
    clutter-1.0-examples \
    kmscube \
"

CORE_IMAGE_WAYLAND_WESTON = " \
    wayland \
    wayland-protocols \
    weston \
    weston-xwayland \
    weston-init \
"

# Packages related to video capture and processing
CORE_IMAGE_VIDEO_PKGS = " \
    kernel-module-v4l2loopback \
    v4l2loopback-utils \
    v4l2loopback-examples \
    v4l2loopback-init \
    v4l-utils \
    media-ctl \
    \
    libatomic \
    python3-pycairo \
    \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-base \
    \
    gstreamer1.0-plugins-base-tcp \
    gstreamer1.0-plugins-base-pango \
    gstreamer1.0-plugins-base-videorate \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-good-jpeg \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-multifile \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-bad \
    \
    gstreamer1.0-python \
"

CORE_IMAGE_ELECTRON_PKGS = " \
    libxcomposite \
    nss \
    libxscrnsaver \
    libxi \
    libxtst \
    libxrandr \
    at-spi2-atk \
    cups \
    gconf \
"

# AWS Greengrass packages
CORE_IMAGE_AWS_GREENGRASS= " \
    aws-iot-greengrass-core-software \
    aws-iot-greengrass-core-software-init \
    aws-iot-greengrass-core-software-certificate \
    aws-iot-greengrass-core-software-device-credentials \
    greengrass-dependency-checker \
    credentials-setup \
    system-status \
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
    ${CORE_IMAGE_ELECTRON_PKGS} \
    ${CORE_IMAGE_DEV_PKGS} \
    ${CORE_IMAGE_PYTHON_PKGS} \
    ${CORE_IMAGE_NODEJS_PKGS} \
    ${CORE_IMAGE_AWS_GREENGRASS} \
    ${CORE_IMAGE_VIDEO_PKGS} \
"

EXTRA_USERS_PARAMS += "\
usermod -a -G docker torizon; \
groupadd -r ggc_group; \
useradd -r -g ggc_group -p '' ggc_user; \
"

IMAGE_INSTALL_remove = "vim-tiny"
