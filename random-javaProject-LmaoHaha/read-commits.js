import fs from 'fs'

const commits = JSON.parse(fs.readFileSync('commits.json', 'utf-8'))
console.log(commits)

//console.log('committed today')
//console.log()
const committers = new Set(commits.map(commit => commit.author?.login))
const everyone = Array.from(
  fs.readFileSync('README.md', 'utf-8').matchAll(/\(https:\/\/github.com\/([\w-]+)\/?\)/g),
  ([, username]) => username
)
const AT = process.env.DONT_PING === 'true' ? '' : '@'

fs.writeFileSync('issuemsg.txt', [
  `these people have committed already: ${[...committers].join(', ')}`,
  `these people are in the repo: ${everyone.join(', ')}`,
  `these people havent committed yet!! ${everyone.filter(user => !committers.has(user)).sort().map(u => `${AT}${u}`).join(', ')} go commit now!!`
].join('\n\n'))

const BASH_TRUE = 0
const BASH_FALSE = 1

process.exit(committers.size === 0 ? BASH_TRUE : BASH_FALSE)
