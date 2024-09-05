document.addEventListener('DOMContentLoaded', function() {
  fetch('https://randomuser.me/api/')
      .then(response => response.json())
      .then(data => {
          const user = data.results[0];

          document.getElementById('user-name').textContent = `${user.name.first} ${user.name.last}`;
          document.getElementById('user-photo').src = user.picture.large;
          document.getElementById('user-email').textContent = `Email: ${user.email}`;
          document.getElementById('user-location').textContent = `Location: ${user.location.city}, ${user.location.country}`;
      })
      .catch(error => {
          console.error('Error fetching the user data:', error);
      });
});

function fetchRandomUser() {
  fetch('https://randomuser.me/api/')
      .then(response => response.json())
      .then(data => {
          const user = data.results[0];

          // Update the card with user information
          document.getElementById('user-name').textContent = `${user.name.first} ${user.name.last}`;
          document.getElementById('user-photo').src = user.picture.large;
          document.getElementById('user-email').textContent = `Email: ${user.email}`;
          document.getElementById('user-location').textContent = `Location: ${user.location.city}, ${user.location.country}`;
      })
      .catch(error => {
          console.error('Error fetching the user data:', error);
      });
}

document.addEventListener('DOMContentLoaded', function() {
  // Fetch user on page load
  fetchRandomUser();

  // Add event listener to the button
  document.getElementById('new-user-button').addEventListener('click', function() {
      fetchRandomUser();
  });
});