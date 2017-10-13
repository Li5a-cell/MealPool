import React, { Component } from 'react'

class Signup extends Component {
  constructor() {
    super()
    this.state = {
      password: '',
      name: '',
      zip: '',
      email: ''
    }
  }
  createUser(evt) {
    evt.preventDefault()
    //API post
  }
  handleFormEntry(evt) {
    evt.preventDefault()
    this.setState({
      [evt.target.name]: evt.target.value
    })
  }
  render() {
    return (
      <div id="signup">
        <div id="signupForm">
          <form>
            <input
              id="name"
              name="name"
              type="text"
              placeholder="Enter your name"
              value={this.state.name}
              onChange={this.handleFormEntry}
            >
            </input>
            <input
              id="zip"
              name="zip"
              type="text"
              placeholder="Enter your zip code"
              value={this.state.zip}
              onChange={this.handleFormEntry}
            >
            </input>
            <input
              id="email"
              name="email"
              type="text"
              placeholder="Enter your email"
              value={this.state.email}
              onChange={this.handleFormEntry}
            >
            </input>
            <input
              id="password"
              name="password"
              type="text"
              placeholder="Enter your password"
              value={this.state.password}
              onChange={this.handleFormEntry}
            >
            </input>
          </form>
        </div>
        <div id="signupBtns">
          <button
            id="signup"
            onClick={this.createUser}
          >
          </button>
        </div>
      </div>
    )
  }
}

export default Signup
