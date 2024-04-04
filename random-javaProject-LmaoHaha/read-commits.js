import fs from 'fs'

const commits = JSON.parse(fs.readFileSync('commits.json', 'utf-8'))
//console.log(commits)

//console.log('committed today')
//console.log()
const committers = new Set(commits.map(commit => commit.author?.login))

fs.writeFileSync('issuemsg.txt', `these people have committed already: ${[...committers].join(', ')}`)

const BASH_TRUE = 0
const BASH_FALSE = 1

process.exit(committers.size === 0 ? BASH_TRUE : BASH_FALSE)
