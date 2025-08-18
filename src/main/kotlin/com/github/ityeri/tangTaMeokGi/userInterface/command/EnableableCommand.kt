package com.github.ityeri.tangTaMeokGi.userInterface.command

import co.aikar.commands.BaseCommand

abstract class EnableableCommand : BaseCommand() {
    abstract fun enable()
}