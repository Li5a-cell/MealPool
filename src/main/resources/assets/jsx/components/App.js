import React, { Component } from 'react'

import NavBar from './NavBar.js'
import MainRouter from './MainRouter.js'

class App extends Component {
  render() {
    return (
      <div>
        <NavBar />
        <MainRouter />
      </div>
    )
  }
}

export default App
