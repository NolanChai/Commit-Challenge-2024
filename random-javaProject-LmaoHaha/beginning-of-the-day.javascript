#!/usr/bin/env node

console.error('right now is ', new Date())
const today = new Date()
console.log(new Date(today.getFullYear(), today.getMonth(), today.getDate()).toISOString())
