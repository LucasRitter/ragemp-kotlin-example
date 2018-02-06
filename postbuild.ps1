param([string]$project="RageMP Kotlin Examples")

Remove-Item client_packages -Recurse -ErrorAction Ignore
New-Item -ItemType directory -Path ./client_packages

(Get-Content ".\out\production\$($project)\lib\RageMP-Kotlin-Client.js").replace("module.exports", 'exports') | Set-Content ./client_packages/ragemp.api.js
(Get-Content ".\out\production\$($project)\lib\kotlin.js").replace("module.exports", "exports") | Set-Content ./client_packages/kotlin.js
(Get-Content ".\out\production\$($project)\$($project).js").replace("module.exports", "exports").Replace("RageMP-Kotlin-Client", "ragemp").Replace("$($project)", "client") | Set-Content ./client_packages/client.js
$javascript = 'const kotlinJS = require("kotlin.js");
const ragempAPI = require("ragemp.api.js");
const ragempClient = require("client.js");
ragempClient.main();'
$javascript | Set-Content ./client_packages/index.js

Remove-Item out -Recurse -ErrorAction Ignore
Remove-Item "$($project).js" -ErrorAction Ignore