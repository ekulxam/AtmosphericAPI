Atmospheric API 3.1.2
- Port to 1.21.10
- Rewrite AtmosphericResourceReader according to Resource Loader changes for 1.21.9+ 
- Add DirectionalParticleRenderer for 1.21.9+
- Remove WaitingOnFabricRenderState due to [FabricMC/fabric#5004](https://github.com/FabricMC/fabric/pull/5004) (1.21.8+)
- Remove command block methods in AtmosphericCommandDirector because I don't want to port them and no one uses them