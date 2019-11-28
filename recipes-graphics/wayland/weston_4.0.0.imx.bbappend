FILESEXTRAPATHS_prepend := "${THISDIR}/weston:"

SRC_URI_append = " \
    file://weston-pasta-demo.ini \
    file://westonPastaBackground.png \
"

do_install_prepend () {
    mv ${WORKDIR}/weston-pasta-demo.ini ${WORKDIR}/weston.ini
    mv ${WORKDIR}/westonPastaBackground.png ${D}${sysconfdir}/westonPastaBackground.png
}

FILES_${PN} += "${sysconfdir}/xdg/weston/*"