FILESEXTRAPATHS_prepend := "${THISDIR}/weston:"

SRC_URI_append = " \
    file://weston-pasta-demo.ini \
    file://westonPastaBackground.png \
"

do_install_prepend () {
    # replace original weston.ini to be used by do_install ()
    cp ${WORKDIR}/weston-pasta-demo.ini ${WORKDIR}/weston.ini

    # add background image to serve as a user-space static splash screen
    install -d ${D}${sysconfdir}/xdg/weston/
    install -m 0644 ${WORKDIR}/westonPastaBackground.png ${D}${sysconfdir}/xdg/weston/westonPastaBackground.png
}

FILES_${PN} += "${sysconfdir}/xdg/weston/*"