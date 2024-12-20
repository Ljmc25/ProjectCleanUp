const ContextPath = getContextPath();
function getContextPath() {
  return sessionStorage.getItem("contextpath");
}