import React, { Component } from 'react'
// import axios from 'axios'

import Signup from './Signup'
import UserProfile from './UserProfile'

class App extends Component {
  constructor() {
    super()
    this.state = {
      isLoggedIn: false
    }
  }
  render() {
    return (
      <div id="main">
        {
          this.state.isLoggedIn ? <UserProfile /> : <Signup />
        }
      </div>
    )
  }
}

export default App
