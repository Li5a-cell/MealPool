import React, { Component } from 'react'

import { Switch, Route } from 'react-router-dom'
import Main from './Main'
import SetGoals from './SetGoals'
import ScheduleEat from './ScheduleEat'
import ScheduleCook from './ScheduleCook'

class MainRouter extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/" component={Main} />
        <Route path="/goals" component={SetGoals} />
        <Route path="/schedule-eat" component={ScheduleEat} />
        <Route path="/schedule-cook" component={ScheduleCook} />
        <Route path="/profile" component={Main} />
      </Switch>
    )
  }
}

export default MainRouter
