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
    this.handleFormEntry = this.handleFormEntry.bind(this)
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
        <h1>Welcome to MealTime</h1><br/>
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
              type="password"
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
            onClick={evt => this.props.login(evt, this.state)}
          >
            Sign Up
          </button>
        </div>
      </div>
    )
  }
}

export default Signup
