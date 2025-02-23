# Use the base image
FROM mcr.microsoft.com/devcontainers/base:debian

# Define build arguments (adjust these to your needs)
ARG NEW_USERNAME=joppe
ARG NEW_UID=1000
ARG NEW_GID=1000

# If the default "vscode" user exists, update its username and UID/GID.
# Otherwise, create a new user.
RUN if id vscode >/dev/null 2>&1; then \
      # Rename "vscode" to your preferred username
      usermod -l ${NEW_USERNAME} vscode && \
      groupmod -n ${NEW_USERNAME} vscode && \
      # Update UID and GID
      usermod --uid ${NEW_UID} --gid ${NEW_GID} ${NEW_USERNAME} && \
      chown -R ${NEW_UID}:${NEW_GID} /home/${NEW_USERNAME}; \
    else \
      groupadd --gid ${NEW_GID} ${NEW_USERNAME} && \
      useradd --uid ${NEW_UID} --gid ${NEW_GID} -m ${NEW_USERNAME}; \
    fi && \
    apt-get update && apt-get install -y sudo && \
    echo "${NEW_USERNAME} ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/${NEW_USERNAME} && \
    chmod 0440 /etc/sudoers.d/${NEW_USERNAME}

# Set the default user
USER ${NEW_USERNAME}
