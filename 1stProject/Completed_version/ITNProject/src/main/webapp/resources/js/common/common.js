const getContextPath = () => {
  return sessionStorage.getItem("contextpath");
}

const ContextPath = getContextPath();