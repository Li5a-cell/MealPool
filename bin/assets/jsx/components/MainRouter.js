import React, { Component } from 'react'
import { Switch, Route } from 'react-router-dom'

import NavBar from './NavBar'
import SetGoals from './SetGoals'
import ScheduleEat from './ScheduleEat'
import ScheduleCook from './ScheduleCook'
import UserProfile from './UserProfile'

class MainRouter extends Component {
  render() {
    return (
      <div id="main">
        <NavBar user={this.props.user} />
        <Switch>
          <Route exact path="/" render={() => <UserProfile user={this.props.user} /> } />
          <Route path="/goals" component={SetGoals} />
          <Route path="/schedule-eat" component={ScheduleEat} />
          <Route path="/schedule-cook" component={ScheduleCook} />
          <Route path="/profile" render={() => <UserProfile user={this.props.user} /> } />
        </Switch>
      </div>
    )
  }
}

export default MainRouter
