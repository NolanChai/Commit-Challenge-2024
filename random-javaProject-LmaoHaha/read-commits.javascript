import fs from 'fs'

const commits = JSON.parse(fs.readFileSync('commits.json', 'utf-8'))
console.log(commits)

console.log('committed today')
console.log(commits.map(commit => commit.author?.login))
