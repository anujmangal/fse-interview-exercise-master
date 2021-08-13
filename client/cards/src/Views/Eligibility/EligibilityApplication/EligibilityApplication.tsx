import React, {useState, MouseEvent} from "react";
import { useFormik } from "formik";
import styled from "styled-components";

import FormInput from "../../../DesignSystem/Form/FormInput";
import SubmitButton from "../../../DesignSystem/Form/SubmitButton";
import Title from "../../../DesignSystem/Title";

const FormWrapper = styled.div`
  flex: 1 1 auto;
  width: 100%;
`;

interface FormValues {
  name: string;
  email: string;
  address: string;
}

interface FunctionFromSuper {
    submitFunction: (id: string) => void;
}


const EligibilityApplication : React.FC<FunctionFromSuper> = (props) => {
    const [formSubmitted, setFormSubmitted]  = useState(false);
    const [requestProcessed, setRequestProcessed] = useState(false);
    const [requestId, setRequestId] = useState('');
    let cardsAllowed = '';

    const checkCardHandler = (event: MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        fetch('http://localhost:8080/applicantEligibility/getCards?reqId='+requestId)
                      .then(res => res.text())
                      .then((cards) => {
                        if(cards === '-1'){
                            setRequestProcessed(false);
                            cardsAllowed = 'Your request is being processed';
                        }
                        else {
                            setRequestProcessed(true);
                            if (cards === ''){
                                cardsAllowed = "No Cards Allowed";
                            }
                            else{
                                cardsAllowed = cards;
                            }
                        }
                        props.submitFunction(cardsAllowed);
                      })
                      .catch(console.log);
        };

  const { handleChange, handleSubmit, values} = useFormik<FormValues>({
    initialValues: {
      name: "",
      email: "",
      address: ""
    },
    onSubmit: (values) => {
    const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(values)
        };
        fetch('http://localhost:8080/applicantEligibility/check', requestOptions)
                .then(res => res.json())
                .then((requestId) => {
                    setRequestId(requestId);
                })
                .catch(console.log);
        setFormSubmitted(true);
    }
  });

  if(!formSubmitted){
  return (
    <FormWrapper>
      <Title>Cards</Title>
      <form onSubmit={handleSubmit}>
        <FormInput
          type="text"
          name="name"
          id="name"
          onChange={handleChange}
          value={values.name}
          placeholder="Name"
        />
        <FormInput
          type="email"
          name="email"
          id="email"
          onChange={handleChange}
          value={values.email}
          placeholder="Email"
        />
        <FormInput
          type="text"
          name="address"
          id="address"
          onChange={handleChange}
          value={values.address}
          placeholder="Address"
        />
        <SubmitButton text="Submit" />
      </form>
    </FormWrapper>
  );
  }
  else if (!requestProcessed){
      return (
        <div><button onClick={checkCardHandler}>Click To Get Results !</button></div>
      );
  }
  else{
        return <div></div>;
  }
};

export default EligibilityApplication;
