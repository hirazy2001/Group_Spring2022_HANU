
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NotFoundLayout from "../layouts/NotFoundLayout";
import AppElement from "./app-element";
import routes from "./routes";

const AppRouter = () => {
 
  return (
    <Router>
      <Routes>
        {routes.map((r) => (
          <Route
            key={r.path}
            path={r.path}
            element={
              <AppElement
                component={r.component}
                isLayout={r.isLayout}
                layout={r.layout}
                authen={r.authen}
                path={r.path}
              />
            }
          />
        ))}
        <Route path="*" element={<NotFoundLayout/>}/>
      </Routes>
    </Router>
  );
};
export default AppRouter;
