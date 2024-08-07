import Button from "components/Button";
import TopBar from "components/TopBar";
import "pages/styles/Login.scss"
import { useNavigate } from "react-router-dom";

interface Props {
    
}

export default function Login (props: Props) {
    const navigate = useNavigate();

    const handleDeveloperLoginClick = () => {

    }

    return (
        <>
            <TopBar />
            <div className="login-container">
                <div className="login-section">
                    <div className="login-section-content">
                        <h2>Login</h2>

                        <div className="radio-group">
                            <label>
                                <input type="radio" name="role" value="recruiter" checked />
                                Recruiter
                            </label>
                            <label>
                                <input type="radio" name="role" value="developer" />
                                Developer
                            </label>
                        </div>

                        <form className="login-form">
                            <div className="form-group">
                                <label htmlFor="username">Username</label>
                                <input type="text" id="username" name="username" required />
                            </div>
                            <div className="form-group">
                                <label htmlFor="password">Password</label>
                                <input type="password" id="password" name="password" required />
                            </div>
                            <Button onClick={() => navigate("/login?user=recruiter")} size="xl">
                                Login
                            </Button>
                            <p className="account-text">Don't have an account? <span className="btn">Register</span>.</p>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}