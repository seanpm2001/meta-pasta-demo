FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# AWS Greengrass demo
AWS = "aws_gg"

# Modify the variable below to select right files for image
BASE = "${AWS}"

MKT = "marketing_${BASE}.tar"
ICON = "toradexlinux_${BASE}.png"
#UBOOT = "uEnv-override_${BASE}.txt"

SRC_URI_append = " \
    file://${MKT};unpack=false \
	file://${ICON} \
"
# file://${UBOOT}

do_deploy_append(){
	rm ${DEPLOYDIR}/marketing.tar
	install -m 644 ${WORKDIR}/${MKT} ${DEPLOYDIR}/marketing.tar

	rm ${DEPLOYDIR}/toradexlinux.png
	install -m 644 ${WORKDIR}/${ICON} ${DEPLOYDIR}/toradexlinux.png

	# cat ${DEPLOYDIR}/uEnv.txt ${WORKDIR}/${UBOOT} > ${DEPLOYDIR}/uEnv-override.txt
	# rm ${DEPLOYDIR}/uEnv.txt
	# install -m 644 ${DEPLOYDIR}/uEnv-override.txt ${DEPLOYDIR}/uEnv.txt
}
