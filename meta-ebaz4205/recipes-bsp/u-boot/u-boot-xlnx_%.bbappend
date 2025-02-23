FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://0001-ebaz4205-add-files.patch \
    "

do_compile:append() {
    oe_runmake spl
}

do_deploy:append() {
    install -m 0644 ${B}/spl/u-boot-spl.bin ${DEPLOYDIR}/BOOT.BIN
    install -m 0644 ${B}/u-boot.elf ${DEPLOYDIR}/u-boot.elf
    install -m 0644 ${B}/u-boot.img ${DEPLOYDIR}/u-boot.img
    install -m 0644 ${B}/dts/dt.dtb ${DEPLOYDIR}/u-boot.dtb
}