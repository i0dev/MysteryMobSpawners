# Mystery Mob Spawners Plugin!
This plugin allows you to give yourself or others a Mystery Mob Spawner, which you can place down, and it will display a nice animation and give you a random spawner from a pre-defined set
This is a PLUGIN, and it goes into your plugins folder on your server. It will not work if its anywhere else!

## How it works:
You can give it with the /mms give command, Then you can place it down anywhere, and it will and it will display a nice animation
 and give you a random spawner from a pre-defined set. The plugin fully configurable including messaging!

### Commands:
*Values in **[]** are required, values in **<>** are optional*
 - ```/mms reload```                   - ``Reloads the plugin.``
 - ```/mms give [Player] <Amount>```   - ``Gives a player a MysteryMobSpawner.``


### Permissions:
 - ```mms.*```        - ``Gives permissions to run any command.``
 - ```mms.give```     - ``Gives permissions to use /mms give.``
 - ```mms.place```    - ``Gives permissions to place MysteryMobSpawners.``
 - ```mms.reload```   - ``Gives permissions to use /mms reload.``
 
### Config.yml, with comments explaining everything.
```yaml
###########################################
#                                         #
#      Plugin made by i01 @i0dev.com      #
#                                         #
###########################################
particlesEnabled: true
givenSpawnerMessage: "&7You have been given a &7%DISPLAYNAME%!"
hologramName: "&c&lMystery Mob Spawner"
mobSpawnerItem:
  material: MOB_SPAWNER
  data: 0
  glowing: true
  name: '&4&lMystery Mob Spawner'
  lore:
    - '&7Right-click to receive a random mob spawner.'
    - ''
    - '&4&lSpawners'
    - ' &4* &7Snowman'
    - ' &4* &7Silverfish'
    - ' &4* &7Witch'
    - ' &4* &7Creeper'
    - ' &4* &7Squid'
    - ' &4* &7Endermite'
spawners:
  'snowman':
    chance: 50
    displayName: "&f&lSnowman &7Spawner"
    command:
      - 'ss give %player% snowman 1'
  'silverfish':
    chance: 40
    displayName: "&7&lSilverfish &7Spawner"
    command:
      - 'ss give %player% silverfish 1'
  'witch':
    chance: 30
    displayName: "&5&lWitch &7Spawner"
    command:
      - 'ss give %player% witch 1'
  'creeper':
    chance: 20
    displayName: "&2&lCreeper &7Spawner"
    command:
      - 'ss give %player% creeper 1'
  'squid':
    chance: 10
    displayName: "&9&lSquid &7Spawner"
    command:
      - 'ss give %player% squid 1'
  'endermite':
    chance: 5
    displayName: "&d&lEndermite &7Spawner"
    command:
      - 'ss give %player% endermite 1'


Messaging:
  Reload_Successful: '&7You successfully reloaded the plugin!'
  Reload_Usage: '&7Please use &9/mms reload &7to reload the plugin!'
  Mystery_Mob_Spawner_Give_Usage: '&7Please use &c/mms Give [Player] <amount>&7!'
  Mystery_Mob_Spawner_Give_Unknown_Member_Error: '&cError: &7Unknown Member. Please try again!'
  Mystery_Mob_Spawner_Give_Amount_Error: '&cError: &7Unknown amount. Please enter a valid
    amount!'
  Mystery_Mob_Spawner_Give_Success: '&aSuccess! &7You gave &c{player} &f{amount} &7Mystery Mob Spawners!'
  Mystery_Mob_Spawner_Received: '&7You received &f{amount} &7Mystery Mob Spawners from &c{player}&7!'
  Mystery_Mob_Spawner_Place_Success: '&a&lYou successfully placed a ChunkBuster! &3You destroyed
    &f{amount} &3blocks!'
  Mystery_Mob_Spawner_Give_No_Permission: '&7You do not have &cmms.give &7permission!'
  Mystery_Mob_Spawner_Place_No_Permission: '&7You do not have &cmms.place &7permission!'
  Reload_No_Permission: '&7You do not have &cmms.reload &7permission!'
```

## Need help? 
Join the [Support Server](https://discord.gg/h7MRPyJ) and feel free to ask any questions to me directly too, My discord is i0#0001
