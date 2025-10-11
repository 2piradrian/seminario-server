import os
import platform
import subprocess
import time

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

services = {
    "posts": os.path.abspath(os.path.join(BASE_DIR, "..", "posts")),
    "user-profiles": os.path.abspath(os.path.join(BASE_DIR, "..", "user-profiles")),
    "users": os.path.abspath(os.path.join(BASE_DIR, "..", "users")),
    "catalog": os.path.abspath(os.path.join(BASE_DIR, "..", "catalog")),
    "page-profiles": os.path.abspath(os.path.join(BASE_DIR, "..", "page-profiles")),
}

delay_seconds = 15

def open_terminal_and_docker_compose(name, path):
    system = platform.system()

    cmd = "docker compose up"

    if system == "Windows":
        subprocess.Popen(f'start cmd /K "cd {path} && {cmd}"', shell=True)
    elif system == "Linux":
        try:
            subprocess.Popen(
                ["gnome-terminal", "--", "bash", "-c", f"cd {path} && {cmd}; exec bash"]
            )
        except FileNotFoundError:
            subprocess.Popen(cmd, cwd=path, shell=True)
    else:
        raise RuntimeError(f"Sistema operativo no soportado: {system}")


def main():
    for svc in ["posts", "user-profiles", "users", "catalog", "page-profiles"]:
        print(f"Iniciando docker compose para {svc}...")
        open_terminal_and_docker_compose(svc, services[svc])
        time.sleep(delay_seconds)


if __name__ == "__main__":
    main()
