import React, {
    useEffect,
    useState,
} from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import "./StudentProfile.css";


const StudentProfile = () => {
    const { id } = useParams();

    const [student, setStudent] = useState({
        firstName: "",
        lastName: "",
        email: "",
        department: "",
    });

    useEffect(() => {
        loadStudent();
    }, []);

    const loadStudent = async () => {
        const result = await axios.get(`http://localhost:8080/students/student/${id}`);
        setStudent(result.data);
    };

    return (
                <div className="row">
                    {/* Avatar Card */}
                    <div className="col-lg-3 mb-4" style={{ maxWidth: "300px"}}>
                        <div className="card text-center shadow" >
                            <div className="card-body">
                                <img
                                    src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                                    alt="avatar"
                                    className="rounded-circle shadow"
                                    style={{width: 130, height: 130, objectFit: "cover"}}
                                />

                                <h5 className="my-3 fw-semibold">
                                    {`${student.firstName} ${student.lastName}`}
                                </h5>
                                <div className="d-flex justify-content-center gap-2">
                                    <button className="btn btn-outline-primary px-4">Call</button>
                                    <button className="btn btn-outline-warning px-4">Message</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Info Card */}
                    <div className="col-lg-9" >
                        <div className="card p-4 shadow" style={{ maxWidth: "800px"}}>
                            <h4 className="mb-4 ">Student Details</h4>

                            <div className="info-item mb-3">
                                <label>First Name</label>
                                <p>{student.firstName}</p>
                            </div>
                            <div className="info-item mb-3">
                                <label>Last Name</label>
                                <p>{student.lastName}</p>
                            </div>
                            <div className="info-item mb-3">
                                <label>Email</label>
                                <p>{student.email}</p>
                            </div>
                            <div className="info-item mb-3">
                                <label>Department</label>
                                <p>{student.department}</p>
                            </div>

                            <div className="text-end mt-4">
                                <Link to="/view-students" className="btn btn-warning px-4">
                                    Cancel
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
    );
};

export default StudentProfile;
