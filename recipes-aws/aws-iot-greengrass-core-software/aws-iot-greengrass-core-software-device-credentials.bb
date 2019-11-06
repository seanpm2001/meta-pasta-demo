SUMMARY = "Greengrass IoT Core device ceritficate"
DESCRIPTION = "Greengrass IoT Core device ceritficate specific to a single \
unique device, which is the Greengrass core device. This certificate should be \
kept secure either offline or in a safe online place but not a public git repo \
"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://core-device-secret.tar.gz;subdir=greengrass "

S = "${WORKDIR}/greengrass"

DEPENDS = " aws-iot-greengrass-core-software aws-iot-greengrass-core-software-certificate "
RDEPENDS_${PN} = " aws-iot-greengrass-core-software aws-iot-greengrass-core-software-certificate "

do_install () {
    install -d ${D}/greengrass/certs/
    cp -a ${S}/certs/* ${D}/greengrass/certs/

    install -d ${D}/greengrass/config/
    install -m 0644 ${S}/config/config.json ${D}/greengrass/config/
}

FILES_${PN} += "/greengrass/certs/* /greengrass/config/*"