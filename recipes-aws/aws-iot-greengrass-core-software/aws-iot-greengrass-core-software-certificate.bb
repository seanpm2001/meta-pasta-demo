SUMMARY = "Amazon trust root CA"
DESCRIPTION = "Amazon trust root CA to be used with Greengrass software"
HOMEPAGE = "https://www.amazontrust.com/repository/"

LICENSE = "CLOSED"

SRC_URI = " https://www.amazontrust.com/repository/AmazonRootCA1.pem "

SRC_URI[md5sum] = "7095142f080d1d25221eec161ff14223"
SRC_URI[sha256sum] = "2c43952ee9e000ff2acc4e2ed0897c0a72ad5fa72c3d934e81741cbd54f05bd1"

S = "${WORKDIR}"

DEPENDS = " aws-iot-greengrass-core-software "
RDEPENDS_${PN} = " aws-iot-greengrass-core-software "

do_install () {
	install -d ${D}/greengrass/certs
    install -m 0644 ${S}/AmazonRootCA1.pem ${D}/greengrass/certs/root.ca.pem
}

FILES_${PN} += "/greengrass/certs/root.ca.pem"