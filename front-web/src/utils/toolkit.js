import { configureStore, createSlice } from "@reduxjs/toolkit";

let loginTokenSlice = createSlice({
  name: "loginTokenSlice",
  initialState: {
    token: localStorage.getItem("token") ? localStorage.getItem("token") : "",
  },
  reducers: {
    changeToken(state, action){
      state.token = action.payload
    }
  }
})

export const {changeToken} = loginTokenSlice.actions

let store = configureStore({
  reducer: {
    loginTokenReducer: loginTokenSlice.reducer
  }
})

export default store