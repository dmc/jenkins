plugins = Jenkins.instance.pluginManager.plugins.collect{ "'$it.shortName'"}
print "[${plugins.join(',\n')}].each{ Hudson.instance.updateCenter.getPlugin(it).deploy() }"