/*

1. My Goals
2. Schedule meals to eat
3. Schedule meals to cook
4. My User Profile

*/

import React, { Component } from 'react'
import { Link } from 'react-router-dom'

class NavBar extends Component {
  render() {
    return (
      <div id="navBar">
        <div className="navBarItem"><Link to="/goals">My Goals</Link></div>
        <div className="navBarItem"><Link to="/schedule-eat">Schedule meals to eat</Link></div>
        <div className="navBarItem"><Link to="/schedule-cook">Schedule meals to cook</Link></div>
        <div className="navBarItem"><Link to={{pathname: "/profile", state: {
          isLoggedIn: true,
          user: this.props.user
        }}}>My Profile</Link></div>
      </div>
    )
  }
}

export default NavBar
