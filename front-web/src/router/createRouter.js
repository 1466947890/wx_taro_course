import routerMap from "./routerMap";
import routerArr from "./routerArr"
import { Route, Routes } from "react-router-dom";

const createRouter = () => {
  return (
    <Routes>
      {
        routerArr.map((item, index) => {
          if (item.children) {
            return (
              <Route key={index} path={item.path} element={routerMap[item.element]}>
                {
                  item.children.map((itemNode) => {
                    return (
                      <Route key={index} path={itemNode.path} element={routerMap[itemNode.element]} />
                        )
                  })
                }
                      </Route>
                    )
                  } else {
            return (
                <Route key={index} path={item.path} element={routerMap[item.element]}></Route>
                )
          }

        })
      }
              </Routes>
            )
          }

          export default createRouter

