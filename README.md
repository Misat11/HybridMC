# HybridMC
[![Build Status](https://jenkins.mtorus.cz/job/HybridMC/badge/icon)](https://jenkins.mtorus.cz/job/HybridMC/)
HybridMC is plugin that allows connection of Bedrock Edition clients on your Java Edition Spigot or Craftbukkit server!

#### This project is in early development. We don't recommend use it on production server.
#### Now only support for 1.13.2 CraftBukkit/Spigot server!!! (1.8 - 1.13.1 is planned but now not working)

## Features and planned features
- [x] Connection to downstream server
- [x] Chat
- [x] Loading map - With bugs
- [x] Moving - With bugs
- [x] Spawning entities - With bugs
- [x] Block breaking - With bugs (breaks invalid block)
- [ ] Block placing - ???
- [ ] Inventory support
- [x] Sounds - Partial support
- [x] Effects - Partial support
- [ ] API for Modal Forms
- [ ] Skins
- [ ] Multiversion - HybridMC can do it, but now there are only one supported version
- [ ] IP forwarding - is needed !!!! (example for Ip Bans)

## Building

You will need Java JDK 8 and above and Maven. Then run command `mvn package`. Jar can be found in `target` folder.

## Installing

- Copy `HybridMC.jar` into your `plugins` folder on your Spigot server
- Start your server for generate `config.yml` in `plugins/HybridMC`
- Edit `config.yml` and restart your server

## Who uses HybridMC
See live stats on BStats[![Bstats](https://bstats.org/signatures/bukkit/HybridMC.svg)](https://bstats.org/plugin/bukkit/HybridMC/)

## Used libraries
* [Network by NukkitX team](https://github.com/NukkitX/Network)
* [MCProtocolLib by Steveice10](https://github.com/Steveice10/MCProtocolLib)
