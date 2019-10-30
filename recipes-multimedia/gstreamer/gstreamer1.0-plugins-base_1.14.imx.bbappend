EXTRA_OECONF_remove = " --disable-introspection "
EXTRA_OECONF_append = " --enable-introspection "

inherit gettext use-imx-headers gobject-introspection

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gst-libs/gst/tag/.libs:${B}/gst-libs/gst/video/.libs:${B}/gst-libs/gst/audio/.libs:${B}/gst-libs/gst/rtp/.libs:${B}/gst-libs/gst/allocators/.libs"
}