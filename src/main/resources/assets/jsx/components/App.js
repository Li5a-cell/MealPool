import React, { Component } from 'react'
// import axios from 'axios'

import MainRouter from './MainRouter'
import Signup from './Signup'

class App extends Component {
  constructor() {
    super()
    this.state = {
      isLoggedIn: false,
      user: {}
    }
    this.fakeLogin = this.fakeLogin.bind(this)
  }
  fakeLogin(evt, user) {
    evt.preventDefault()
    this.setState({
      isLoggedIn: true,
      user: {
        name: user.name,
        email: user.email,
        zip: user.zip
      }
    })
  }
  render() {
    return (
      <div id="main">
        {
          this.state.isLoggedIn ? <MainRouter user={this.state.user} /> : <Signup login={this.fakeLogin} />
        }
      </div>
    )
  }
}

export default App
