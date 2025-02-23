FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://0001-ebaz4205-add-files.patch \
    "

do_compile:append() {
    oe_runmake spl
}

do_deploy:append() {
    install -m 0644 ${B}/spl/boot.bin ${DEPLOYDIR}/boot.bin
}