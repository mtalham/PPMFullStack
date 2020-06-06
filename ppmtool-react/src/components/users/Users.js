import React from "react";
import { useMutation, useQuery } from "@apollo/react-hooks";
import { ADD_USER_TO_PROJECT, USERS_QUERY } from "./UsersApollo";
import AddUserToProject from "./AddUserToProject";
import Modal from "react-modal";

const createOptions = options =>
  options.map(option => ({
    value: option.username,
    label: option.fullName
  }));

const customStyles = {
  content: {
    top: "50%",
    left: "50%",
    marginRight: "-50%",
    width: "50%",
    transform: "translate(-50%, -50%)"
  }
};

const Users = ({ openModal, setOpenModal, projectId }) => {
  const { data, loading } = useQuery(USERS_QUERY);
  const [addUserToProject] = useMutation(ADD_USER_TO_PROJECT);

  if (loading || !data) return "Loading...";

  const options = data && createOptions(data.users);

  const handleOnSubmit = values => {
    addUserToProject({
      variables: { username: values.username.value, projectId: projectId }
    }).then(res => {
      if (res.data.addUserToProject === true) {
        setOpenModal(false);
      }
    });
  };

  return (
    <div className="card-form">
      <Modal
        isOpen={openModal}
        onRequestClose={() => setOpenModal(false)}
        style={customStyles}
        ariaHideApp={false}
      >
        <AddUserToProject options={options} onSubmit={handleOnSubmit} />
      </Modal>
      {/*<SuggestSelect*/}
      {/*  options={options}*/}
      {/*  value={selectedOption}*/}
      {/*  handleChange={setSelectedOption}*/}
      {/*/>*/}
    </div>
  );
};

export default Users;
