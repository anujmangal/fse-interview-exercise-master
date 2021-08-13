import React from "react";
import styled from "styled-components";

const ResultsWrapper = styled.div`
  flex: 1 1 auto;
  padding-top: 48px;
  justify-content: center;
  margin: 0 -8px;
  display: flex;
  flex-wrap: wrap;
`;

interface ResultValues {
  cardsAllowed: string;
}

const EligibilityResults : React.FC<ResultValues> = (props) => {
    return <ResultsWrapper>{props.cardsAllowed}</ResultsWrapper>;
};

export default EligibilityResults;
