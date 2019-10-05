import React from "react";
import Select from "react-select";

const SuggestSelect = ({ options, value, handleChange }) => (
  <Select
    options={options}
    value={value}
    onChange={option => handleChange(option)}
  />
);

export const RenderSelectInput = ({ input, options }) => (
  <Select
    {...input}
    onChange={value => input.onChange(value)}
    onBlur={() => input.onBlur(input.value)}
    options={options}
  />
);

export default SuggestSelect;
