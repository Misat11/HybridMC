# HybridMC
HybridMC is plugin that allows connection of Bedrock Edition clients on your Java Edition Spigot or Craftbukkit server!

## Features and planned features
- [x] Connection to downstream server
- [x] Chat
- [x] Loading map - With bugs
- [x] Moving - With bugs
- [x] Spawning entities - With bugs
- [x] Block breaking - With bugs (breaks invalid block)
- [ ] Block placing
- [ ] Inventory support
- [x] Sounds - Partial support
- [ ] Effects
- [ ] API for Modal Forms
- [ ] Skins
- [ ] Multiversion

## Building

You will need Java JDK 8 and above and Maven. Then run command `mvn package`. Jar can be found in `target` folder.

## Installing

- Copy `HybridMC.jar` into your `plugins` folder on your Spigot server
- Start your server for generate `config.yml` in `plugins/HybridMC`
- Edit `config.yml` and restart your server

## Used libraries
* [Network by NukkitX team](https://github.com/NukkitX/Network)
* [MCProtocolLib by Steveice10](https://github.com/Steveice10/MCProtocolLib)